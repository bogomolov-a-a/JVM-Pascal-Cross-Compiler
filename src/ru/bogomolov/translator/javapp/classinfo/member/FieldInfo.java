package ru.bogomolov.translator.javapp.classinfo.member;

import java.io.DataInputStream;
import java.io.IOException;

import ru.bogomolov.translator.javapp.classinfo.ClassFileHeader;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;

public class FieldInfo
	extends MemberInfo
{	
	protected FieldInfo(DataInputStream dis,
		ConstantPool constantPool,
		ClassFileHeader header)throws IOException
	{
		super(dis,
			constantPool,
			header);
	}
}