package tdt4140.gr1806.app.core;
/**
 * An interface with methods that classes representing objects of the database must implement to get its data.
 */

import java.sql.Connection;

public abstract class ActiveDomainObject {

	public abstract void init(Connection conn);
	public abstract void refresh(Connection conn);
	public abstract void save(Connection conn);
	
}
