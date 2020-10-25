package ru.bogomolov.translator.javapp.classinfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPoolEntry;
import ru.bogomolov.translator.javapp.classinfo.member.ClassMemberInfo;
/**
Information about class file.
Support class file from JVM version 8. Can read (and write, but later) class file.
@since 0.0.1
@author Bogomomolov Artem A.(artem.bogomolov.a@gmail.com)
*/
public class ClassFile 
{	
	/**
		Class header,contains JVM magic marker and JVM version.
	*/
	private ClassFileHeader header;	
	/**
	Class constant pool.<br/>
	From: https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.5
	Java Virtual Machine instructions do not rely on the run-time layout of classes, interfaces, class instances, or arrays.
	Instead, instructions refer to symbolic information in the constant_pool table.
	All constant_pool table entries have the following general format:

	cp_info {
		u1 tag;
		u1 info[];
	}
	Each item in the constant_pool table must begin with a 1-byte tag indicating the kind of cp_info entry. 
	The contents of the info array vary with the value of tag.
	The valid tags and their values are listed in Table 4.4-A.
	Each tag byte must be followed by two or more bytes giving information about the specific constant.
	The format of the additional information varies with the tag value.
	For any types implemented some method for representing info.
	*/	
	private ConstantPool constantPool;
	/**
	Current class info: access flags and link to constant pool item.
	If class info entry not detected, throw IOException.
	*/
	private CurrentClassInfo currentClassInfo;	
    /**
	InheritanceInfo contains data about superclass(if not present, then null entry -&gt; Object class)
	and all implemented interfaces.
	*/
	private InheritanceInfo inheritanceInfo;
	/**
	Information about fields, methods and attributes class files.
	For feature reading use ClassFileHeader version data.
	*/
	private ClassMemberInfo classMemberInfo;
	
	public ClassFile(InputStream is)throws IOException
	{
		try(DataInputStream dis=new DataInputStream(is))
		{
			read(dis);			
		}
	}
	public ConstantPoolEntry getConstantPoolEntry(short index)
	{
		return constantPool.getEntry(index);
	}
	private void read(DataInputStream dis)throws IOException
	{
		header=new ClassFileHeader(dis);
		constantPool=new ConstantPool(dis);
		currentClassInfo=new CurrentClassInfo(dis,
			constantPool);
		inheritanceInfo=new InheritanceInfo(dis,
			constantPool);
		classMemberInfo=new ClassMemberInfo(dis,
			constantPool,
			header);
		if(dis.available()>0)
		{
			throw new ClassFormatError("available unread data!");
		}
	}
	public String getJVMVersion()
	{
		return header.getJVMVersion();
	}
	public CurrentClassInfo getCurrentClassInfo()
	{
		return currentClassInfo;
	}
	public InheritanceInfo getInheritanceInfo()
	{
		return inheritanceInfo;
	}
	public ClassMemberInfo getClassMemberInfo()
	{
		return classMemberInfo;
	}
	
}