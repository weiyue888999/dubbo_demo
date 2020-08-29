package pers.xiaomo.demo.dubbo.ext;

import org.apache.dubbo.common.context.FrameworkExt;

public abstract class AbstractFrameworkExtAdaptor implements FrameworkExt{
	
	private enum Status{
		UNINITIALIZED,
		INITIALIZED,
		STARTED,
		DESTROYED
	}
	private Status currentStatus = Status.UNINITIALIZED;
	
    public synchronized final void initialize() throws IllegalStateException{
    	if(this.currentStatus == Status.UNINITIALIZED){
    		doInitialize();
    		this.currentStatus = Status.INITIALIZED;
    	}
    }

    public synchronized final void start() throws IllegalStateException{
    	if(this.currentStatus == Status.INITIALIZED){
    		doStart();
    		this.currentStatus = Status.STARTED;
    	}
    }

    public synchronized final void destroy() throws IllegalStateException{
    	doDestroy();
    	this.currentStatus = Status.DESTROYED;
    }
    
    protected void doInitialize() throws IllegalStateException{
    	
    }

    protected void doStart() throws IllegalStateException{
    	
    }

    protected void doDestroy() throws IllegalStateException{
    	
    }
}
