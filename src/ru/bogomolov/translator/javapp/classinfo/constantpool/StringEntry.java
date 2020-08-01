package ru.bogomolov.translator.javapp.classinfo.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class StringEntry
{
	private final short stringIndex;
	StringEntry(DataInputStream dis)throws IOException
	{
		stringIndex=dis.readShort();
	}
	public short asStringIndex()
	{
		return stringIndex;
	}
}