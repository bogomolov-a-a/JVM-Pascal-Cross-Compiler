package ru.bogomolov.translator.javapp.classinfo.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class ClassEntry
{
	private final short classNameIndex;
	ClassEntry(DataInputStream dis)throws IOException
	{
		classNameIndex=dis.readShort();
	}
	public short getClassNameIndex()
	{
		return classNameIndex;
	}
}