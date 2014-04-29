package com.webpagebytes.cms;

import java.io.IOException;
import java.io.OutputStream;

import com.webpagebytes.cms.appinterfaces.WBContentProvider;
import com.webpagebytes.cms.appinterfaces.WBModel;
import com.webpagebytes.cms.cmsdata.WBFile;
import com.webpagebytes.cms.cmsdata.WBWebPage;
import com.webpagebytes.cms.exception.WBException;

public class WBDefaultContentProvider implements WBContentProvider {

	FileContentBuilder fileContentBuilder;
	PageContentBuilder pageContentBuilder;
	@Override
	public boolean writeFileContent(String externalKey, OutputStream os) 
	{
		try
		{
			WBFile file = fileContentBuilder.find(externalKey);
			if (null == file)
			{
				return false;
			}
			fileContentBuilder.writeFileContent(file, os);
		}
		catch (WBException e)
		{
			// log error
			return false;
		}
		return true;
	}

	@Override
	public boolean writePageContent(String externalKey, WBModel model,
			OutputStream os) 
	{
		try
		{
			WBWebPage wbWebPage = pageContentBuilder.findWebPage(externalKey);
			String content = pageContentBuilder.buildPageContent(wbWebPage, model);
			os.write(content.getBytes("UTF-8"));
		} catch (WBException e)
		{
			return false;
		}
		catch (IOException e)
		{
			return false;
		}
		return true;
	}

}
