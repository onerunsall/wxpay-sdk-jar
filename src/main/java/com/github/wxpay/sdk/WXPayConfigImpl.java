package com.github.wxpay.sdk;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class WXPayConfigImpl extends WXPayConfig {
	private String appId;
	private String mchId;
	private String key;

	private byte[] certData;
	// private static WXPayConfigImpl INSTANCE;

	public WXPayConfigImpl(String appId, String mchId, String key, String cerPath) throws Exception {
		// String certPath = "D://CERT/common/apiclient_cert.p12";
		InputStream certStream = null;
		try {
			if (cerPath != null && !cerPath.isEmpty()) {
				File file = new File(cerPath);
				certStream = new FileInputStream(file);
				this.certData = new byte[(int) file.length()];
				certStream.read(this.certData);
				certStream.close();
			}
			this.appId = appId;
			this.mchId = mchId;
			this.key = key;
		} catch (Exception e) {
			throw e;
		} finally {
			if (certStream != null)
				certStream.close();
		}
	}

	public WXPayConfigImpl(String appId, String mchId, String key) {
		this.appId = appId;
		this.mchId = mchId;
		this.key = key;
	}

	// public static WXPayConfigImpl getInstance() throws Exception {
	// if (INSTANCE == null) {
	// synchronized (WXPayConfigImpl.class) {
	// if (INSTANCE == null) {
	// INSTANCE = new WXPayConfigImpl();
	// }
	// }
	// }
	// return INSTANCE;
	// }

	public String getAppID() {
		return appId;
	}

	public String getMchID() {
		return mchId;
	}

	public String getKey() {
		return key;
	}

	public InputStream getCertStream() {
		ByteArrayInputStream certBis;
		certBis = new ByteArrayInputStream(this.certData);
		return certBis;
	}

	public int getHttpConnectTimeoutMs() {
		return 2000;
	}

	public int getHttpReadTimeoutMs() {
		return 10000;
	}

	IWXPayDomain getWXPayDomain() {
		return WXPayDomainSimpleImpl.instance();
	}

	public String getPrimaryDomain() {
		return WXPayConstants.DOMAIN_API;
	}

	public String getAlternateDomain() {
		return WXPayConstants.DOMAIN_API2;
	}

	@Override
	public int getReportWorkerNum() {
		return 1;
	}

	@Override
	public int getReportBatchSize() {
		return 2;
	}
}
