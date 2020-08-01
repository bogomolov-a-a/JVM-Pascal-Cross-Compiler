package ru.bogomolov.translator.javapp.classinfo.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodHandleEntry
{
	private final short referenceKind;
	private final short referenceIndex;
	MethodHandleEntry(DataInputStream dis)throws IOException
	{
		referenceKind=dis.readShort();
		referenceIndex=dis.readShort();			
	}
	public short getReferenceKind()
	{
		return referenceKind;
	}
	public short getReferenceIndex()
	{
		return referenceIndex;
	}
}