/*
 * Copyright 2009 Sven Meier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wicketbox;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.lang.Args;

import wicketbox.util.CollectionFormattable;

/**
 * A behavior which allows resizing of columns between a master and a slave..
 * 
 * @author svenmeier
 */
public class ResizableColumns extends AbstractBoxBehavior
{

	/**
	 * Default maximum age for cookie persistence.
	 */
	private static final int MAX_AGE = 30 * 24 * 60 * 60;

	private static final long serialVersionUID = 1L;

	private IModel<List<Integer>> widths;

	private String headerSelector;

	private String bodySelector;

	public ResizableColumns(String headerSelector, String bodySelector, IModel<List<Integer>> widths)
	{
		Args.notNull(widths, "widths");

		this.headerSelector = headerSelector;
		this.bodySelector = bodySelector;
		this.widths = widths;
	}

	@Override
	public void detach(Component component)
	{
		super.detach(component);

		this.widths.detach();
	}

	@Override
	public final void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);

		String initJS = String.format("wicketbox.resizableColumns('%s',{'header': '%s', 'body': '%s'},%s,%s);",
				component.getMarkupId(), headerSelector, bodySelector, getPersist(component),
				new CollectionFormattable(this.widths.getObject()));
		response.render(OnDomReadyHeaderItem.forScript(initJS));
	}

	/**
	 * @see AbstractBoxBehavior#persistInCookie(String)
	 */
	protected String getPersist(Component component)
	{
		return persistInCookie("resizableColumns:" + component.getPageRelativePath(), MAX_AGE);
	}

	@Override
	protected void respond(AjaxRequestTarget target)
	{
		final RequestCycle requestCycle = RequestCycle.get();

		final String parameter = requestCycle.getRequest().getRequestParameters()
				.getParameterValue("value").toString();

		List<Integer> widths = new ArrayList<Integer>();
		for (String string : parameter.split(":"))
		{
			try
			{
				widths.add(Integer.parseInt(string));
			}
			catch (Exception noInteger)
			{
				break;
			}
		}

		this.widths.setObject(widths);

		onResized(target);
	}

	/**
	 * See
	 * {@link AbstractBoxBehavior#persistOnServer(String, java.nio.channels.Channel)}
	 */
	protected void onResized(AjaxRequestTarget target)
	{
	}
}