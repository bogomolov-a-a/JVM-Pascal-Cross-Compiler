package ru.bogomolov.translator.javapp.classinfo.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

class DWordEntry
{
	private final int highBytes;
	private final int lowBytes;
	DWordEntry(DataInputStream dis)throws IOException
	{
		highBytes=dis.readInt();
		lowBytes=dis.readInt();
	}
	long asLong()
	{
		return fromParts();
	}
	double asDouble()
	{
		return (float)fromParts();
	}
	private long fromParts()
	{
		return ((long)(highBytes << 31)) + lowBytes;
	}
}