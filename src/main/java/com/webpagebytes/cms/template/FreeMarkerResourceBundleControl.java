/*
 *   Copyright 2014 Webpagebytes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package com.webpagebytes.cms.template;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.webpagebytes.cms.WPBMessagesCache;

public class FreeMarkerResourceBundleControl extends ResourceBundle.Control {

	protected WPBMessagesCache messageCache;
	
	FreeMarkerResourceBundleControl(WPBMessagesCache messageCache)
	{
		this.messageCache = messageCache;
	}
	
    public List<String> getFormats(String baseName) {
        if (baseName == null)
            throw new NullPointerException();
        return FORMAT_PROPERTIES;
    }

	public ResourceBundle newBundle(String baseName,
            Locale locale,
            String format,
            ClassLoader loader,
            boolean reload) throws IllegalAccessException, InstantiationException, IOException 
    {
		return new CmsResourceBundle(messageCache, locale);
	}
	
	public long getTimeToLive(String baseName,
            Locale locale)
	{
		return 0;
	}
	public boolean needsReload(String baseName,
            Locale locale,
            String format,
            ClassLoader loader,
            ResourceBundle bundle,
            long loadTime)
	{
		CmsResourceBundle wbresource = (CmsResourceBundle) bundle;
		if (!wbresource.getFingerPrint().equals(messageCache.getFingerPrint(locale)))
		{
			return true;
		}
		return false;
	}
}
