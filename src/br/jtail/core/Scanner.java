package br.jtail.core;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class Scanner
{

	public static DataFile readToDataFile(final String fileName, final String filePath, final int skip)
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

			int size = 1024 * 1024;
			int bytes = 0;
			byte[] buff = new byte[size];

			if (dis.available() != 0)
			{

				dis.skip(skip);
				bytes = dis.read(buff);
				result = new DataFile().bytes(bytes).fileName(fileName).value(new String(buff));

			}

			// dispose all the resources after using them.
			fis.close();
			bis.close();
			dis.close();

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return result;
	}

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
			return String.format("{ \"fileName\" : \"%s\", \"off\":\"%d\", \"value\":\"%s\"}", this.fileName, this.bytes, this.value);

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
