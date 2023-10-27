package com.highq.labels.collaborate;

import java.util.Locale;
import com.highq.utils.helper.ResourceBundleReader;

public class GlobalLabels
{

	static Locale locale = new Locale("en", "GB");
	static ResourceBundleReader collaborateLabels = new ResourceBundleReader("resourcebundle",
			locale);
	// static ResourceBundleReader collaborateLabels = new ResourceBundleReader();

	protected static String getRBValue(String propertyName)
	{
		return collaborateLabels.getRBValue(propertyName);
	}

}
