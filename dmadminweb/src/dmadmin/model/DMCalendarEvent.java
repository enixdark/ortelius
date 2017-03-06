/*
 *
 *  DeployHub is an Agile Application Release Automation Solution
 *  Copyright (C) 2017 Catalyst Systems Corporation DBA OpenMake Software
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package dmadmin.model;

import dmadmin.DMEventType;
import dmadmin.ObjectType;
import dmadmin.json.IJSONSerializable;
import dmadmin.json.LinkField;

public class DMCalendarEvent implements java.io.Serializable {
	private static final long serialVersionUID = -5362995729983900561L;
	private String m_EventTitle;
	private String m_EventDesc;
	private int m_id;
	protected int m_creator;
	protected int m_created;
	protected int m_modifier;
	protected int m_modified;
	private int m_appid;
	private int m_envid;
	private int m_start;
	private int m_end;
	private int m_deployid;
	private boolean m_AllDay;
	private boolean m_Pending;
	private DMEventType m_EventType;
	private int m_Approver;
	private int m_Approved;
	public DMCalendarEvent() {
		m_creator=0;
		m_modifier=0;
		m_created=0;
		m_modified=0;
		m_id=0;
		m_EventTitle="";
		m_appid=0;
		m_envid=0;
		m_start=0;
		m_end=0;
		m_EventType=DMEventType.NOEVENT;
		m_deployid=0;
		m_Approver=0;
		m_Approved=0;
    }
	public void setID(int id) {
		m_id = id;
	}
	public void setDeployID(int deployid) {
		m_deployid = deployid;
	}
	public void setEventTitle(String eventTitle) {
		m_EventTitle = eventTitle;
	}
	public void setEventDesc(String eventDesc) {
		m_EventDesc = eventDesc;
	}
	public void setAppID(int appid) {
		m_appid = appid;
	}
	public void setCreatorID(int creator) {
		m_creator = creator;
	}
	public void setModifierID(int modifier) {
		m_modifier = modifier;
	}
	public int getCreated()   { return m_created; }
	public void setCreated(int created)   { m_created = created; }

	public int getModified()   { return m_modified; }
	public void setModified(int modified)   { m_modified = modified; }
	
	public void setEnvID(int envid) {
		m_envid = envid;
	}
	public void setStart(int start) {
		m_start = start;
	}
	public void setEnd(int end) {
		m_end = end;
	}
	public void setAllDayEvent(boolean AllDay) {
		m_AllDay = AllDay;
	}
	
	public void setEventType(DMEventType EventType) {
		m_EventType = EventType;
	}
	
	public void setPending(boolean pending) {
		m_Pending = pending;
	}
	
	public void setApprover(int approver) {
		m_Approver = approver;
	}
	
	public void setApproved(int approved) {
		m_Approved = approved;
	}
	
	public int getID() {
		return m_id;
	}
	public int getDeployID() {
		return m_deployid;
	}
	public String getEventTitle() {
		return m_EventTitle;
	}
	public String getEventDesc() {
		return m_EventDesc;
	}
	public int getAppID() {
		return m_appid;
	}
	public int getCreatorID() {
		return m_creator;
	}
	public int getEnvID() {
		return m_envid;
	}
	public int getStart() {
		return m_start;
	}
	public int getEnd() {
		return m_end;
	}
	public boolean getAllDayEvent() {
		return m_AllDay;
	}
	public DMEventType getEventType() {
		return m_EventType;
	}
	public String getEventTypeString() {
		String et = m_EventType.toString();
		return Character.toUpperCase(et.charAt(0))+et.substring(1).toLowerCase();
	}
	
	public boolean getPending() {
		return m_Pending;
	}
	
	public int getApprover() {
		return m_Approver;
	}
	
	public int getApproved() {
		return m_Approved;
	}
	
	public boolean isApproved() {
		return m_Approved>0;
	}

	public IJSONSerializable getLinkJSON() {
		return new LinkField(ObjectType.CALENDAR_ENTRY, m_id, m_EventTitle);
	}
}
