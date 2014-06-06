package br.jtail.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.jtail.core.Constants;
import br.jtail.core.JTailException;
import br.jtail.core.Prop;
import br.jtail.util.NumberUtil;

/**
 *
 * Just file's conf stored
 *
 * @author fabiozoroastro@gmail.com
 *
 *
 */
public class FileConf
{

	private String name;
	private String path;
	private Integer maxBuffer;
	private String group;

	/**
	 * return collection of files configured on conf.properties
	 *
	 * @return
	 * @throws JTailException
	 *
	 * @author fabiozoroastro@gmail.com
	 */
	public static Map<String, FileConf> getAllFileConfs() throws JTailException
	{
		Set<Object> keys = Prop.get().getProperties().keySet();

		Map<String, FileConf> mapFiles = new HashMap<String, FileConf>();
		FileConf currentFileOnMap = null;
		String strKey = null;
		String currentPath = null;
		Integer currentMaxBuffer = null;

		for (Object key : keys)
		{
			strKey = key.toString();
			currentFileOnMap = mapFiles.get(strKey.replaceAll(Constants.Def.MAX_BUFFER_SUFIX, ""));

			if (currentFileOnMap == null)
			{
				currentFileOnMap = new FileConf();
			}

			if (strKey.endsWith(Constants.Def.MAX_BUFFER_SUFIX))
			{
				currentMaxBuffer = NumberUtil.toInteger(Prop.get().getProperties().getProperty(strKey));
				currentFileOnMap.maxBuffer(currentMaxBuffer);
				strKey = strKey.substring(0, strKey.length() - Constants.Def.MAX_BUFFER_SUFIX.length());
			}
			else
			{
				currentPath = Prop.get().getProperties().getProperty(strKey);
				currentFileOnMap.path(currentPath);
			}
			currentFileOnMap.name(strKey);

			mapFiles.put(strKey, currentFileOnMap);

		}

		return mapFiles;
	}

	public FileConf()
	{
		this(null);
	}

	public FileConf(String name, String path, Integer maxBuffer, String group)
	{
		this.name = name;
		this.path = path;
		this.maxBuffer = maxBuffer;
		this.group = group;

	}

	public FileConf(String name, String path)
	{
		this(name, path, null, null);
	}

	public FileConf(String path)
	{
		this(null, path);
	}

	public FileConf name(String name)
	{
		this.setName(name);
		return this;
	}

	public FileConf path(String path)
	{
		this.setPath(path);
		return this;
	}

	public FileConf maxBuffer(Integer maxBuffer)
	{
		this.setMaxBuffer(maxBuffer);
		return this;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public Integer getMaxBuffer()
	{
		return maxBuffer;
	}

	public void setMaxBuffer(Integer maxBuffer)
	{
		this.maxBuffer = maxBuffer;
	}

	public String getGroup()
	{
		return group;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

}
