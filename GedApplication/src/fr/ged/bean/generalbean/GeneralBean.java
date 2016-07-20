/**
 * Managed Bean for JSF
 * Bean BASE for shared methods 
 * @author SHA
 */

package fr.ged.bean.generalbean;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * The Class generalBean.
 */
public class GeneralBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    public static final FacesMessage.Severity LEVEL_ERROR = FacesMessage.SEVERITY_ERROR;

    public static final FacesMessage.Severity LEVEL_FATAL = FacesMessage.SEVERITY_FATAL;

    public static final FacesMessage.Severity LEVEL_INFO = FacesMessage.SEVERITY_INFO;

    public static final FacesMessage.Severity LEVEL_WARN = FacesMessage.SEVERITY_WARN;

    /**
     * Gets the String with the translation.
     * 
     * @param keyTranslate the key to translate
     * @return the String with translate
     * @author SHA
     */
    public String getTranslate(String keyTranslate) {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("MessageResources", locale);
        String msg = null;

        msg = bundle.getString(keyTranslate);
        return msg;

    }

    /**
     * Send message to context of JSF for error messages.
     * 
     * @param keyTranslate the key to translate
     * @param level the level of severity
     * @param id the id
     * @author SHA
     */
    protected void sendMessageJSF(String keyTranslate, FacesMessage.Severity level, String id) {

        String msg = null;
        FacesContext contexto = FacesContext.getCurrentInstance();

        try {
            msg = getTranslate(keyTranslate);
            FacesMessage mensaje = new FacesMessage(level, msg, "");
            contexto.addMessage(id, mensaje);
        } catch (MissingResourceException m) {
            contexto.addMessage(id, new FacesMessage(level, msg, msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void sendDirectMessageJSF(String messageText, FacesMessage.Severity level, String id) {

        FacesContext contexto = FacesContext.getCurrentInstance();

        try {
            FacesMessage mensaje = new FacesMessage(level, messageText, "");
            contexto.addMessage(id, mensaje);
        } catch (MissingResourceException m) {
            contexto.addMessage(id, new FacesMessage(level, messageText, messageText));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Send message to context of JSF for error messages.
     * 
     * @param keyTranslate the key to translate
     * @param level the level of severity
     * @author SHA
     */
    protected void sendMessageJSF(String keyTranslate, FacesMessage.Severity nivel) {
        sendMessageJSF(keyTranslate, nivel, null);
    }

}
