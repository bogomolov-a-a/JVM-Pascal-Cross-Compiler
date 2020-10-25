package ru.bogomolov.translator.javapp.classinfo.member;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import ru.bogomolov.translator.javapp.classinfo.ClassFileHeader;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;

abstract class MemberInfo
{
	protected final short             accessFlags;
	protected final short            nameIndex;
	protected final short             descriptorIndex;
    protected final short            attributesCount;
	protected final List<AttributeInfo> attributes;
		
	protected MemberInfo(DataInputStream dis,
		ConstantPool constantPool,
		ClassFileHeader header)throws IOException		
	{
		accessFlags=dis.readShort();
		nameIndex=dis.readShort();
		descriptorIndex=dis.readShort();
		attributesCount=dis.readShort();		
		System.out.println("attribute count "+attributesCount);
		attributes=new ArrayList(attributesCount);
		for(int i=0;i<attributesCount;i++)
		{
			attributes.add(new AttributeInfo(dis,
				constantPool,
				header));
		}
	}				
}