package ru.bogomolov.translator.javapp.classinfo.member;

import java.io.DataInputStream;
import java.io.IOException;
import ru.bogomolov.translator.javapp.classinfo.ClassFileHeader;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;

public class AttributeInfoList
	extends MemberInfoList<AttributeInfo>
{
	protected AttributeInfoList(DataInputStream dis,
		ConstantPool constantPool,		
		ClassFileHeader header)throws IOException
	{
		super(dis,
			constantPool,
			header);
	}
	@Override
	protected AttributeInfo createMemberFromInputStream(DataInputStream dis,
		ConstantPool constantPool,		
		ClassFileHeader header)throws IOException
	{
		return new AttributeInfo(dis,
			constantPool,
			header);
	}
}