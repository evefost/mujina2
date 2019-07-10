package mujina.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.saml.key.JKSKeyManager;
import org.springframework.stereotype.Component;

@Component
public class SpConfiguration extends SharedConfiguration {

  private String defaultEntityId;

  private String defaultIdpSSOServiceURL;
  private String idpSSOServiceURL;
  private String defaultProtocolBinding;
  private String protocolBinding;
  private String defaultAssertionConsumerServiceURL;
  private boolean defaultNeedsSigning;
  private String assertionConsumerServiceURL;
  private String spPrivateKey;
  private String spCertificate;

  @Autowired
  public SpConfiguration(JKSKeyManager keyManager,
                         @Value("${sp.base_url}") String spBaseUrl,
                         @Value("${sp.entity_id}") String defaultEntityId,
                         @Value("${sp.single_sign_on_service_location}") String defaultIdpSSOServiceURL,
                         @Value("${sp.acs_location_path}") String defaultAssertionConsumerServiceURLPath,
                         @Value("${sp.protocol_binding}") String defaultProtocolBinding,
                         @Value("${sp.private_key}") String spPrivateKey,
                         @Value("${sp.certificate}") String spCertificate,
                         @Value("${sp.needs_signing}") boolean needsSigning) {
    super(keyManager);
    this.setDefaultEntityId(defaultEntityId);
    this.setDefaultIdpSSOServiceURL(defaultIdpSSOServiceURL);
    this.setDefaultAssertionConsumerServiceURL(spBaseUrl + defaultAssertionConsumerServiceURLPath);
    this.setDefaultProtocolBinding(defaultProtocolBinding);
    this.setSpPrivateKey(spPrivateKey);
    this.setSpCertificate(spCertificate);
    this.setDefaultNeedsSigning(needsSigning);
    reset();
  }

  @Override
  public void reset() {
    setEntityId(defaultEntityId, false);
    setNeedsSigning(defaultNeedsSigning);
    resetKeyStore(defaultEntityId, spPrivateKey, spCertificate);
    setIdpSSOServiceURL(defaultIdpSSOServiceURL);
    setProtocolBinding(defaultProtocolBinding);
    setAssertionConsumerServiceURL(defaultAssertionConsumerServiceURL);
    setSignatureAlgorithm(getDefaultSignatureAlgorithm());
  }

  public String getDefaultEntityId() {
    return defaultEntityId;
  }

  public void setDefaultEntityId(String defaultEntityId) {
    this.defaultEntityId = defaultEntityId;
  }

  public String getDefaultIdpSSOServiceURL() {
    return defaultIdpSSOServiceURL;
  }

  public void setDefaultIdpSSOServiceURL(String defaultIdpSSOServiceURL) {
    this.defaultIdpSSOServiceURL = defaultIdpSSOServiceURL;
  }

  public String getIdpSSOServiceURL() {
    return idpSSOServiceURL;
  }

  public void setIdpSSOServiceURL(String idpSSOServiceURL) {
    this.idpSSOServiceURL = idpSSOServiceURL;
  }

  public String getDefaultProtocolBinding() {
    return defaultProtocolBinding;
  }

  public void setDefaultProtocolBinding(String defaultProtocolBinding) {
    this.defaultProtocolBinding = defaultProtocolBinding;
  }

  public String getProtocolBinding() {
    return protocolBinding;
  }

  public void setProtocolBinding(String protocolBinding) {
    this.protocolBinding = protocolBinding;
  }

  public String getDefaultAssertionConsumerServiceURL() {
    return defaultAssertionConsumerServiceURL;
  }

  public void setDefaultAssertionConsumerServiceURL(String defaultAssertionConsumerServiceURL) {
    this.defaultAssertionConsumerServiceURL = defaultAssertionConsumerServiceURL;
  }

  public boolean isDefaultNeedsSigning() {
    return defaultNeedsSigning;
  }

  public void setDefaultNeedsSigning(boolean defaultNeedsSigning) {
    this.defaultNeedsSigning = defaultNeedsSigning;
  }

  public String getAssertionConsumerServiceURL() {
    return assertionConsumerServiceURL;
  }

  public void setAssertionConsumerServiceURL(String assertionConsumerServiceURL) {
    this.assertionConsumerServiceURL = assertionConsumerServiceURL;
  }

  public String getSpPrivateKey() {
    return spPrivateKey;
  }

  public void setSpPrivateKey(String spPrivateKey) {
    this.spPrivateKey = spPrivateKey;
  }

  public String getSpCertificate() {
    return spCertificate;
  }

  public void setSpCertificate(String spCertificate) {
    this.spCertificate = spCertificate;
  }
}
