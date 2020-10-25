package ru.bogomolov.translator.javapp.classinfo.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

class WordEntry
{
	private final int bytes;
	WordEntry(DataInputStream dis)throws IOException
	{
		bytes=dis.readInt();
	}
	
	int asInteger()
	{
		return bytes;
	}
	float asFloat()
	{
		return (float)bytes;
	}
}