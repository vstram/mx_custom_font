// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package mendixsso.actions;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import mendixsso.implementation.utils.StaleDBObjectCleaner;
import mendixsso.proxies.Token;
import mendixsso.proxies.constants.Constants;
import java.time.Instant;

public class DeleteExpiredTokens extends CustomJavaAction<java.lang.Boolean>
{
	private final java.lang.Boolean removeAll;

	public DeleteExpiredTokens(
		IContext context,
		java.lang.Boolean _removeAll
	)
	{
		super(context);
		this.removeAll = _removeAll;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		final String expiryFieldName = removeAll ? "createdDate" : "ExpiresAt";
		final long totalDeleted = StaleDBObjectCleaner.cleanupStaleObjects(Token.class, Token.entityName, expiryFieldName, Instant.now().toEpochMilli(), Constants.getBatchSize().intValue());
		LOG_NODE.info(String.format("Total %d tokens are deleted", totalDeleted));
		return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "DeleteExpiredTokens";
	}

	// BEGIN EXTRA CODE
	static final ILogNode LOG_NODE = Core.getLogger(Constants.getLogNode());
	// END EXTRA CODE
}
