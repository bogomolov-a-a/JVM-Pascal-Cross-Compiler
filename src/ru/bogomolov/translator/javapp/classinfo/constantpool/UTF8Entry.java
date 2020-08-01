package ru.bogomolov.translator.javapp.classinfo.constantpool;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

class UTF8Entry
{		
	private final String data;
	UTF8Entry(DataInputStream dis)throws IOException
	{
		short length=dis.readShort();
		byte[] utf8Data=new byte[length];
		dis.readFully(utf8Data);			
		data=new String(utf8Data,StandardCharsets.UTF_8);
	}
	String asString()
	{
		return data;
	}
}