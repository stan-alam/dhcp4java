/*
 *	This file is part of dhcp4java, a DHCP API for the Java language.
 *	(c) 2006 Stephan Hadinger
 *
 *	This library is free software; you can redistribute it and/or
 *	modify it under the terms of the GNU Lesser General Public
 *	License as published by the Free Software Foundation; either
 *	version 2.1 of the License, or (at your option) any later version.
 *
 *	This library is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *	Lesser General Public License for more details.
 *
 *	You should have received a copy of the GNU Lesser General Public
 *	License along with this library; if not, write to the Free Software
 *	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.dhcpcluster.filter;

import java.util.ArrayList;
import java.util.List;

public abstract class ListFilter implements RequestFilter {

	protected final List<RequestFilter> filters;
	
	public ListFilter() {
		this.filters = new ArrayList<RequestFilter>();
	}
	
	public ListFilter(RequestFilter[] filters) {
		if (filters == null) {
			throw new NullPointerException("filters must not be null");
		}
		this.filters = new ArrayList<RequestFilter>(filters.length);
		for (RequestFilter element : filters) {
			this.filters.add(element);
		}
	}
	
	public ListFilter(List<RequestFilter> filters) {
		if (filters == null) {
			throw new NullPointerException("filters must not be null");
		}
		this.filters = new ArrayList<RequestFilter>(filters.size());
		for (RequestFilter element : filters) {
			this.filters.add(element);
		}
	}

	/**
	 * @return Returns the filters.
	 */
	public List<RequestFilter> getFilters() {
		return filters;
	}
}