package com.zfsoft.spi;

import org.apache.dubbo.common.context.FrameworkExt;
import org.apache.dubbo.common.extension.SPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zfsoft.comp.AbstractFrameworkExtAdaptor;

@SPI
public class DemoFrameworkExt extends AbstractFrameworkExtAdaptor implements FrameworkExt {
	
	private static final Logger log = LoggerFactory.getLogger(DemoFrameworkExt.class);

	@Override
	public void doInitialize() throws IllegalStateException {
		log.info("initialize:"+this);
	}

	@Override
	public void doStart() throws IllegalStateException {
		log.info("start"+this);
	}

	@Override
	public void doDestroy() throws IllegalStateException {
		log.info("destroy"+this);
	}
}
