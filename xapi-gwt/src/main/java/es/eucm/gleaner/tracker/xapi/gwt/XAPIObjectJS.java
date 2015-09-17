package es.eucm.gleaner.tracker.xapi.gwt;

import com.google.gwt.core.client.JavaScriptObject;
import es.eucm.gleaner.tracker.model.traces.InputTrace;
import es.eucm.gleaner.tracker.model.traces.xapi.XAPIObject;

import java.util.Map;

public class XAPIObjectJS extends JavaScriptObject {

	protected XAPIObjectJS() {

	}

	public final void set(XAPIObject statement) {
		init();
		if (statement.getActor().getObjectType() != null) {
			setActorObjectType(statement.getActor().getObjectType());
		}
		if (statement.getActor().getName() != null) {
			setActorName(statement.getActor().getName());
		}
		if (statement.getActor().getAccount().getHomePage() != null) {
			setAccountHomePage(statement.getActor().getAccount().getHomePage());
		}
		if (statement.getActor().getAccount().getName() != null) {
			setAccountName(statement.getActor().getAccount().getName());
		}

		if (statement.getVerb().getId() != null) {
			setVerbId(statement.getVerb().getId());
		}
		if (statement.getVerb().getDisplay() != null) {
			for (Map.Entry<String, String> displayEntry : statement.getVerb()
					.getDisplay().entrySet()) {
				setDisplayValue(displayEntry.getKey(), displayEntry.getValue());
			}
		}

		if (statement.getObject().getObjectType() != null) {
			setObjectObjectType(statement.getObject().getObjectType());
		}
		if (statement.getObject().getId() != null) {
			setObjectId(statement.getObject().getId());
		}
		if (statement.getObject().getDefinition().getType() != null) {
			setDefinitionType(statement.getObject().getDefinition().getType());
		}
		if (statement.getObject().getDefinition().getExtensions() != null) {
			for (Map.Entry<String, Object> extensionsEntry : statement
					.getObject().getDefinition().getExtensions().entrySet()) {
				if (extensionsEntry.getValue() != null) {
					setExtensionsValue(extensionsEntry.getKey(),
							extensionsEntry.getValue().toString());
				}
			}
		}
	}

	public native final void init()/*-{
									this.actor = {
									    account: { }
									};
									this.verb = {
									    display: { }
									};
									this.object = {
									    definition: {
									        extensions: { }
									    }
									};
									}-*/;

	public native final void setActorObjectType(String objectType)/*-{
																	this.actor.objectType = objectType;
																	}-*/;

	public native final void setAccountHomePage(String homePage)/*-{
																this.actor.account.homePage = homePage;
																}-*/;

	public native final void setAccountName(String name)/*-{
														this.actor.account.name = name;
														}-*/;

	public native final void setActorName(String name)/*-{
														this.actor.name = name;
														}-*/;

	public native final void setVerbId(String id)/*-{
													this.verb.id = id;
													}-*/;

	public native final void setDisplayValue(String key, String value)/*-{
																		this.verb.display[key] = value;
																		}-*/;

	public native final void setObjectObjectType(String objectType)/*-{
																	this.object.objectType = objectType;
																	}-*/;

	public native final void setObjectId(String id)/*-{
													this.object.id = id;
													}-*/;

	public native final void setDefinitionType(String type)/*-{
															this.object.definition.type = type;
															}-*/;

	public native final void setExtensionsValue(String key, String value)/*-{
																			this.object.definition.extensions[key] = value;
																			}-*/;

	public final XAPIObject getXAPIObject() {
		return new XAPIObject();
	}
}
