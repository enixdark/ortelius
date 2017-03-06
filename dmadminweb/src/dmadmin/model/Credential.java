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

import dmadmin.DMSession;
import dmadmin.ObjectType;
import dmadmin.PropertyDataSet;
import dmadmin.SummaryChangeSet;
import dmadmin.SummaryField;
import dmadmin.json.IJSONSerializable;
import dmadmin.json.LinkField;

public class Credential
	extends DMObject
{
	private static final long serialVersionUID = 5047906217038129076L;
	
	private CredentialKind m_kind;
	private String m_varusername;
	private String m_varpassword;
	private String m_plainusername;
	private String m_filename;
	private DMSession session;

	public Credential(DMSession session, int id, String name) {
		super(session, id, name);
		this.session = session;
	}
	
 public Credential(DMSession session, int id, String name, int domainid) {
  super(session, id, name);
  this.setDomainId(domainid);
  this.session = session;
 }
 
	public CredentialKind getKind()  { return m_kind; }
	public void setKind(CredentialKind kind)  { m_kind = kind; }
	
	public String getVarUsername()  { return m_varusername; }
	public void setVarUsername(String vu)  { m_varusername = vu; }

	public String getVarPassword()  { return m_varpassword; }
	public void setVarPassword(String vp)  { m_varpassword = vp; }
	
	public String getFilename()  { return m_filename; }
	public void setFilename(String fn)  { m_filename = fn; }
	
	public void setPlainUsername(String un) { m_plainusername = un; }
	public String getPlainUsername() { return m_plainusername; }
	
	public String getKindAsString() {
		if(m_kind != null) {
			switch(m_kind) {
			case UNCONFIGURED:    			return "unconfigured"; //"Use Dialog"
			case ENCRYPTED:     			return "Encrypted";
			case IN_DATABASE:  				return "Encrypted in Database";
			case RTI3_DFO_IN_FILESYSTEM:    return "RTI DFO in Filesystem";
			case FROM_VARS: 				return "From Vars";
			case PPK:						return "Private Key";
			case HARVEST_DFO_IN_FILESYSTEM:	return "Harvest DFO in Filesystem";
			}
		}
		return "unconfigured";
	}
	
	public boolean isKindUnconfigured() {
		return (m_kind == CredentialKind.UNCONFIGURED);
	}

	
	@Override
	public ObjectType getObjectType() {
		return ObjectType.CREDENTIALS;
	}

	@Override
	public String getDatabaseTable() {
		return "dm_credentials";
	}

	@Override
	public String getForeignKey() {
		return "credid";
	}

	@Override
	public IJSONSerializable getSummaryJSON() {
		PropertyDataSet ds = new PropertyDataSet();
		ds.addProperty(SummaryField.NAME, "Name", getName());
		ds.addProperty(SummaryField.SUMMARY, "Summary", getSummary());
		ds.addProperty(SummaryField.CRED_KIND, "Kind",
			new LinkField(ObjectType.CREDENTIAL_KIND,
				(m_kind != null) ? m_kind.value() : 0, getKindAsString()));
		ds.addProperty(SummaryField.OWNER, "Owner", (m_owner != null) ? m_owner.getLinkJSON()
				: ((m_ownerGroup != null) ? m_ownerGroup.getLinkJSON() : null));
		System.out.println("m_plainusername=["+m_plainusername+"]");
		if (m_plainusername != null) {
			// We're displaying the unencrypted username
			ds.addProperty(SummaryField.CRED_USERNAME, "Username", m_plainusername);
		} else {
			ds.addProperty(SummaryField.CRED_ENCUSERNAME, "Username", (String) null);
		}
		ds.addProperty(SummaryField.CRED_VARUSERNAME, "Username", m_varusername);
		ds.addProperty(SummaryField.CRED_ENCPASSWORD, "Password", (String) null);
		ds.addProperty(SummaryField.CRED_VARPASSWORD, "Password", m_varpassword);
		ds.addProperty(SummaryField.CRED_FILENAME, "Filename", m_filename);
		addCreatorModifier(ds);
		return ds.getJSON();
	}

	@Override
	public boolean updateSummary(SummaryChangeSet changes) {
		return m_session.updateCredential(this, changes);
	}

	@Override
	public boolean hasReadWrite() {
		return true;
	}

	@Override
	public String getReadTitle() {
		return "Read Access";
	}

 public IJSONSerializable getLinkJSON() {
  String fullname = session.getDomainName(getDomainId()) + "." + m_name;
  return new LinkField(getObjectType(), m_id, fullname);
 }
}
