package ru.bogomolov.translator.javapp.classinfo.member;

import java.io.DataInputStream;
import java.io.IOException;
import ru.bogomolov.translator.javapp.classinfo.ClassFileHeader;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;

public class ClassMemberInfo
{
	private final FieldInfoList fieldList;
	private final MethodInfoList methodList;
	private final AttributeInfoList attributeList;   
	public ClassMemberInfo(DataInputStream dis,
		ConstantPool constantPool,
		ClassFileHeader header)throws IOException	
	{
		fieldList=new FieldInfoList(dis,
			constantPool,
			header);
		methodList=new MethodInfoList(dis,
			constantPool,
			header);
		attributeList=new AttributeInfoList(dis,
			constantPool,
			header);
	}
	public FieldInfoList getFieldList()
	{
		return fieldList;
	}
	public MethodInfoList getMethodList()
	{
		return methodList;
	}
	public AttributeInfoList getAttributeList()
	{
		return attributeList;
	}
}