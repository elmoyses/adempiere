package de.metas.adempiere.form.zk;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.Check;
import org.adempiere.util.concurrent.FutureValue;
import org.adempiere.util.concurrent.InstantFuture;
import org.adempiere.webui.event.ZkInvokeLaterSupport;
import org.adempiere.webui.window.FDialog;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.DesktopUnavailableException;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

import de.metas.adempiere.form.AbstractClientUIInstance;
import de.metas.web.component.FilePreviewWindow;

abstract class AbstractZkClientUIInstance extends AbstractClientUIInstance
{
	public AbstractZkClientUIInstance()
	{
	}

	protected abstract Desktop getDesktop();

	protected abstract Component getEventComponent();

	/**
	 * 
	 * @return {@link Desktop}; never returns null
	 * @throws IllegalStateException if desktop not found
	 */
	protected static Desktop retrieveCurrentExecutionDesktop()
	{
		final Execution execution = Executions.getCurrent();
		if (execution == null)
		{
			throw new IllegalStateException("ZkClientInstanceUI shall be initialized in an UI Thread (i.e. current execution shall be available)");
		}

		final Desktop desktop = execution.getDesktop();
		if (desktop == null)
		{
			throw new IllegalStateException("Execution " + execution + " does not have any desktop");
		}

		return desktop;
	}

	public <T> T executeInUI(final Callable<T> callable)
	{
		final Future<T> future = executeAsyncUI(callable);

		try
		{
			final T value = future.get();
			return value;
		}
		catch (Exception e)
		{
			throw new AdempiereException(e.getLocalizedMessage(), e);
		}

	}

	public <T> Future<T> executeAsyncUI(final Callable<T> callable)
	{
		if (isInUIThread())
		{
			try
			{
				final T value = callable.call();
				return new InstantFuture<T>(value);
			}
			catch (Exception e)
			{
				throw new AdempiereException(e.getLocalizedMessage(), e);
			}
		}

		final FutureValue<T> result = new FutureValue<T>();

		final Callable<T> callableInEventListener = asInEventListenerCallable(callable, result);
		final Callable<T> callableInUIThread = asInUIThreadCallable(callableInEventListener);

		try
		{
			callableInUIThread.call();
		}
		catch (Exception e)
		{
			throw new AdempiereException(e.getLocalizedMessage(), e);
		}

		return result;
	}

	protected boolean isInUIThread()
	{
		return Executions.getCurrent() != null;
	}

	public <T> Callable<T> asInUIThreadCallable(final Callable<T> callable)
	{
		return new Callable<T>()
		{

			@Override
			public T call() throws Exception
			{
				final boolean inUIThread = isInUIThread();

				Desktop desktopActivated = null;

				try
				{
					if (!inUIThread)
					{
						final Desktop desktop = getDesktop();
						if (Executions.activate(desktop, 10 * 60 * 1000)) // 10 minutes timeout
						{
							desktopActivated = desktop;
						}
						else
						{
							throw new DesktopUnavailableException("Timeout activating desktop.");
						}
					}

					return callable.call();
				}
				finally
				{
					if (desktopActivated != null)
					{
						Executions.deactivate(desktopActivated);
						desktopActivated = null;
					}
				}
			}
		};
	}

	public <T> Callable<T> asInEventListenerCallable(final Callable<T> callable, final FutureValue<T> result)
	{
		return new Callable<T>()
		{

			@Override
			public T call() throws Exception
			{
				final boolean inUIThread = Executions.getCurrent() != null;
				Check.errorUnless(inUIThread, "Not running in UI thread");

				if (Events.inEventListener())
				{
					try
					{
						final T value = callable.call();
						result.set(value);
					}
					catch (Exception e)
					{
						result.setError(e);
					}

					// actual result will be available in FutureValue
					return null;
				}

				final Component eventComponent = getEventComponent();
				final String eventName = "onZKClientUIExecuteUIAsync" + UUID.randomUUID();

				eventComponent.addEventListener(eventName, new EventListener()
				{

					@Override
					public void onEvent(Event event)
					{
						eventComponent.removeEventListener(eventName, this);
						try
						{
							final T value = callable.call();
							result.set(value);
						}
						catch (Exception e)
						{
							result.setError(e);
						}
					}
				});

				Events.echoEvent(eventName, eventComponent, null);

				// actual result will be available in FutureValue
				return null;
			}
		};
	}

	@Override
	public void info(final int WindowNo, final String AD_Message)
	{
		executeInUI(new Callable<Object>()
		{
			@Override
			public Object call() throws Exception
			{
				FDialog.info(WindowNo, null, AD_Message);
				return null;
			}
		});
	}

	@Override
	public void info(final int WindowNo, final String AD_Message, final String message)
	{
		executeInUI(new Callable<Object>()
		{
			@Override
			public Object call() throws Exception
			{
				FDialog.info(WindowNo, null, AD_Message, message);
				return null;
			}
		});
	}

	@Override
	public boolean ask(int WindowNo, String AD_Message)
	{
		final String message = null;
		return ask(WindowNo, AD_Message, message);
	}

	@Override
	public boolean ask(final int WindowNo, final String AD_Message, final String message)
	{
		return executeInUI(new Callable<Boolean>()
		{

			@Override
			public Boolean call() throws Exception
			{
				return FDialog.ask(WindowNo, null, AD_Message);
			}
		});
	}

	@Override
	public void warn(final int WindowNo, final String AD_Message)
	{
		executeInUI(new Callable<Object>()
		{
			@Override
			public Object call() throws Exception
			{
				FDialog.warn(WindowNo, null, AD_Message);
				return null;
			}
		});
	}

	@Override
	public void warn(final int WindowNo, final String AD_Message, final String message)
	{
		executeInUI(new Callable<Object>()
		{
			@Override
			public Object call() throws Exception
			{
				FDialog.warn(WindowNo, null, AD_Message, message);
				return null;
			}
		});
	}

	@Override
	public void error(final int windowNo, final String AD_Message)
	{
		executeInUI(new Callable<Object>()
		{
			@Override
			public Object call() throws Exception
			{
				FDialog.error(windowNo, AD_Message);
				return null;
			}
		});
	}

	@Override
	public void error(final int windowNo, final String AD_Message, final String message)
	{
		executeInUI(new Callable<Object>()
		{
			@Override
			public Object call() throws Exception
			{
				FDialog.error(windowNo, AD_Message, message);
				return null;
			}
		});
	}

	@Override
	public void download(final byte[] data, final String contentType, final String filename)
	{
		executeInUI(new Callable<Object>()
		{
			@Override
			public Object call() throws Exception
			{
				FilePreviewWindow.openNewWindow(data, contentType, filename);
				return null;
			}
		});
	}

	@Override
	public void invokeLater(int windowNo, Runnable runnable)
	{
		ZkInvokeLaterSupport.invokeLater(windowNo, runnable);
	}
}
