/*******************************************************************************
 * Copyright (c) 2014, 2016 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *     Red Hat Inc - change lifecycle model
 *******************************************************************************/
package org.eclipse.neoscada.protocol.iec60870.server.data;

import org.eclipse.neoscada.protocol.iec60870.apci.MessageChannel;
import org.eclipse.neoscada.protocol.iec60870.asdu.MessageManager;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.MessageRegistrator;
import org.eclipse.neoscada.protocol.iec60870.server.Server;
import org.eclipse.neoscada.protocol.iec60870.server.ServerModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.socket.SocketChannel;

public class DataModule implements ServerModule
{
    private final static Logger logger = LoggerFactory.getLogger ( DataModule.class );

    private final DataModel dataModel;

    private final DataModuleOptions options;

    public DataModule ( final DataModuleOptions options, final DataModel dataModel )
    {
        this.options = options;
        this.dataModel = dataModel;
    }

    @Override
    public void initializeServer ( final Server server, final MessageManager manager )
    {
        new MessageRegistrator ().register ( manager );
    }

    @Override
    public void dispose ()
    {
        this.dataModel.stop ();
    }

    @Override
    public void initializeChannel ( final SocketChannel channel, final MessageChannel messageChannel )
    {
        logger.debug ( "Init channel: {}", channel );
        this.dataModel.start ();
        channel.pipeline ().addLast ( new DataModuleHandler ( this.options, messageChannel, this.dataModel ) );
    }
}
