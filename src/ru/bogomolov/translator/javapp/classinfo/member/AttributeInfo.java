package ru.bogomolov.translator.javapp.classinfo.member;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import ru.bogomolov.translator.javapp.classinfo.ClassFileHeader;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPoolEntry;

public class AttributeInfo
{
	private final String attributeType;
	private final int attributeLength;
	private byte[] info;
	public AttributeInfo(DataInputStream dis,
		ConstantPool constantPool,
		ClassFileHeader header)throws IOException		
	{
		System.out.println(header.getJVMVersion());
		short attributeNameIndex=dis.readShort();	
		ConstantPoolEntry entry=constantPool.getEntry(attributeNameIndex);		
		if(!entry.hasValue())
		{
			throw new IOException("wrong attribute format");
		}
		attributeType=entry.asUTF8();
		System.out.println("attribute type is '"+attributeType+"'");
		attributeLength=dis.readInt();
		info=new byte[attributeLength];
		dis.readFully(info);
		System.out.println(Arrays.toString(info));		
	}		
}