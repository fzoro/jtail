package br.jtail.core;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.json.JSONStringer;

/**
 * Scanner is reponsible for the read and skip parts of the file
 *
 * @author fabiozoroastro@gmail.com
 */
public final class Scanner
{

	/**
	 *
	 * Main method. Read informed file and return DataFile
	 *
	 * @see DataFile
	 * @param fileName
	 *            filename registered
	 * @param filePath
	 *            full path
	 * @param skip
	 *            in bytes
	 * @return
	 *
	 * @author fabiozoroastro@gmail.com
	 * @throws JTailException
	 */
	public static DataFile readToDataFile(final String fileName, final String filePath, int skip) throws JTailException
	{
		File file = new File(filePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;

		DataFile result = null;

		try
		{
			fis = new FileInputStream(file);

			// Here BufferedInputStream is added for fast reading.
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			int bytes = (int) file.length();
			byte[] buff = new byte[bytes];

			// skip greater than file(in bytes)
			if (skip > bytes)
			{
				skip = 0;
			}
			// size to read
			int size = bytes - skip;

			String txt = "";

			if (dis.available() != 0)
			{

				// read in the past call
				dis.skip(skip);

				// only accept max-buffer
				maxBuffer(fileName, dis, (bytes - skip));

				// read to buff
				dis.read(buff);

				// to string
				txt = new String(buff, 0, size);

			}

			// create result. ;)
			result = new DataFile().bytes(bytes).fileName(fileName).value(txt);

			// dispose all the resources after using them.
			fis.close();
			bis.close();
			dis.close();

		}
		catch (Exception e)
		{
			new JTailException(e);
		}

		return result;
	}

	/**
	 *
	 *
	 * @param fileName
	 * @param dis
	 * @param fileLength
	 *            current file-length(Consider skip if exists)
	 * @throws JTailException
	 *
	 * @author fabiozoroastro@gmail.com
	 * @throws IOException
	 */
	private static void maxBuffer(String fileName, DataInputStream dis, int fileLength) throws JTailException, IOException
	{

		String fileMaxBuffer = fileName + Constants.Def.MAX_BUFFER_SUFIX;

		int maxKBytes = Constants.Def.NEGATIVE_INDEX;
		try
		{
			String maxBuffer = Prop.get().getProperties().getProperty(fileMaxBuffer);
			if (maxBuffer != null)
			{
				maxKBytes = Integer.valueOf(maxBuffer);
			}
		}
		catch (Exception e)
		{
			// do nothing
		}

		if (maxKBytes > Constants.Def.NEGATIVE_INDEX)
		{
			// K1 = 1024
			int skip = fileLength - (maxKBytes * Constants.Def.K1);

			if (skip > 0)
			{
				dis.skip(skip);
			}
		}

	}

	/**
	 *
	 *
	 * @author fabiozoroastro@gmail.com
	 */
	public static class DataFile
	{

		private String fileName;

		private int bytes;

		private String value;

		public DataFile()
		{

		}

		@Override
		public String toString()
		{
			// return String.format("{ \"fileName\" : \"%s\", \"off\":\"%d\", \"value\":\"%s\"}", this.fileName, this.bytes, this.value);
			return new JSONStringer().object().key("fileName").value(this.fileName).key("off").value(this.bytes).key("value").value(this.value).endObject().toString();

		}

		public DataFile bytes(int bytes)
		{
			this.bytes = bytes;
			return this;
		}

		public DataFile value(String value)
		{
			this.value = value;
			return this;
		}

		public DataFile fileName(String fileName)
		{
			this.fileName = fileName;
			return this;
		}

		public int getBytes()
		{
			return bytes;
		}

		public String getValue()
		{
			return value;
		}

		public String getFileName()
		{
			return fileName;
		}

	}
}
