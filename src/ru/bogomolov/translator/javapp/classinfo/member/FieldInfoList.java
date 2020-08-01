package ru.bogomolov.translator.javapp.classinfo.member;

import java.io.DataInputStream;
import java.io.IOException;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;
import ru.bogomolov.translator.javapp.classinfo.ClassFileHeader;

public class FieldInfoList
	extends MemberInfoList<FieldInfo>
{
	protected FieldInfoList(DataInputStream dis,
		ConstantPool constantPool,		
		ClassFileHeader header)throws IOException
	{
		super(dis,
			constantPool,
			header);
	}
	@Override
	protected FieldInfo createMemberFromInputStream(DataInputStream dis,
		ConstantPool constantPool,		
		ClassFileHeader header)throws IOException
	{
		return new FieldInfo(dis,
			constantPool,
			header);
	}
}