package ru.bogomolov.translator.javapp.classinfo.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public  class InvokeDynamicEntry
{
	private final short bootstrapMethodAttrIndex;
	private final short nameAndTypeMemberIndex;
	 InvokeDynamicEntry(DataInputStream dis)throws IOException
	{
		bootstrapMethodAttrIndex=dis.readShort();
		nameAndTypeMemberIndex=dis.readShort();
	}
	public short getBootstrapMethodAttrIndex()
	{
		return bootstrapMethodAttrIndex;
	}
	public short getNameAndTypeMemberIndex()
	{
		return nameAndTypeMemberIndex;
	}
}