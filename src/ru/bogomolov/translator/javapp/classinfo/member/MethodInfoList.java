package ru.bogomolov.translator.javapp.classinfo.member;

import java.io.DataInputStream;
import java.io.IOException;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;
import ru.bogomolov.translator.javapp.classinfo.ClassFileHeader;

public class MethodInfoList 
	extends MemberInfoList<MethodInfo>
{
	protected MethodInfoList(DataInputStream dis,
		ConstantPool constantPool,		
		ClassFileHeader header)throws IOException
	{
		super(dis,
			constantPool,
			header);
	}
	@Override
	protected MethodInfo createMemberFromInputStream(DataInputStream dis,
		ConstantPool constantPool,		
		ClassFileHeader header)throws IOException
	{
		return new MethodInfo(dis,
			constantPool,
			header);
	}	
}