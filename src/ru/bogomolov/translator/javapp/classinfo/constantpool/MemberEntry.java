package ru.bogomolov.translator.javapp.classinfo.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public  class MemberEntry
{
	private final short classIndex;
	private final short nameAndTypeMemberIndex;
	MemberEntry(DataInputStream dis)throws IOException
	{
		classIndex=dis.readShort();
		nameAndTypeMemberIndex=dis.readShort();
	}
	short getClassIndex()
	{
		return classIndex;
	}
	short getNameAndTypeMemberIndex()
	{
		return nameAndTypeMemberIndex;
	}
}