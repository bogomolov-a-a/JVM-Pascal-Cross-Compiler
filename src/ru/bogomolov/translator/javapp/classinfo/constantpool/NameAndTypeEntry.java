package ru.bogomolov.translator.javapp.classinfo.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class NameAndTypeEntry
{
	private final short nameIndex;
	private final short descriptorIndex;
	NameAndTypeEntry(DataInputStream dis)throws IOException
	{
		nameIndex=dis.readShort();
		descriptorIndex=dis.readShort();
	}
	public short getNameIndex()
	{
		return nameIndex;
	}
	public short getDescriptorIndex()
	{
		return descriptorIndex;
	}
}