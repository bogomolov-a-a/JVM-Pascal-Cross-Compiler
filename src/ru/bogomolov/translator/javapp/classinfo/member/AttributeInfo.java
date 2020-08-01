package ru.bogomolov.translator.javapp.classinfo.member;

import java.io.DataInputStream;
import java.io.IOException;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPoolEntry;

public class AttributeInfo
{
	private final String attributeType;
	private final int attributeLength;
	private byte[] info;
	public AttributeInfo(DataInputStream dis,
		ConstantPool constantPool)throws IOException		
	{
		short attributeNameIndex=dis.readShort();	
		entry=constantPool.getEntry(attributeNameIndex);		
		if(!entry.hasValue())
		{
			throw new IOException("wrong attribute format");
		}
		attributeType=entry.asUtf8();
		attributeLength=dis.readInt();
		info=new byte[attributeLength];
		for(int i=0;i<attributeLength;i++)
		{
			info[i]=dis.readByte();
		}
	}		
}