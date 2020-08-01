package ru.bogomolov.translator.javapp.classinfo.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public  class MethodTypeEntry
{
	private final short descriptorIndex;
	MethodTypeEntry(DataInputStream dis)throws IOException
	{
		descriptorIndex=dis.readShort();
	}
	public short getDescriptorIndex()
	{
		return descriptorIndex;
	}
}