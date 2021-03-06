/*******************************************************************************
 * Copyright (c) 2012, 2013 TH4 SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     TH4 SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package org.eclipse.scada.vi.ui.chart.draw2d.view;

import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.scada.vi.ui.user.viewer.ViewInstance;
import org.eclipse.scada.vi.ui.user.viewer.ViewInstanceDescriptor;
import org.eclipse.scada.vi.ui.user.viewer.ViewInstanceFactory;
import org.eclipse.scada.vi.ui.user.viewer.ViewManager;
import org.eclipse.scada.vi.ui.user.viewer.ViewManagerContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.services.IEvaluationService;

public class ChartViewFactory implements ViewInstanceFactory
{

    @Override
    public ViewInstance createViewInstance ( final ViewManager viewManager, final ViewManagerContext viewManagerContext, final ViewInstanceDescriptor descriptor, final Composite viewHolder, final ResourceManager manager, final IWorkbenchPartSite site )
    {
        final ChartView view = new ChartView ( viewManagerContext, manager, descriptor, viewHolder, (IEvaluationService)site.getService ( IEvaluationService.class ), true );
        view.init ();
        return view;
    }

}
