package ru.bogomolov.translator.javapp.classinfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;
/**
Information about class file.<br/>
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
	Java Virtual Machine instructions do not rely on the run-time layout of classes, interfaces, class instances, or arrays. Instead, instructions refer to symbolic information in the constant_pool table.
	All constant_pool table entries have the following general format:

	cp_info {
		u1 tag;
		u1 info[];
	}
	Each item in the constant_pool table must begin with a 1-byte tag indicating the kind of cp_info entry. The contents of the info array vary with the value of tag.
	The valid tags and their values are listed in Table 4.4-A.
	Each tag byte must be followed by two or more bytes giving information about the specific constant.
	The format of the additional information varies with the tag value.
	For any types implemented some method for representing info.
	*/	
	private ConstantPool constantPool;
	/**
	Current class info: access flags and link to constant pool item.<br/>
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
	
	private FieldInfoList fieldList;
	private MethodInfoList methodList;
	private AttributeInfoList attributeList;   
    public short             fields_count;
    public MemberInfo[]     fields;
    public short             methods_count;
    public MemberInfo[]    methods;
    public short             attributes_count;
    public AttributeInfo[] attributes;
	
	public ClassFile(InputStream is)throws IOException
	{
		try(DataInputStream dis=new DataInputStream(is))
		{
			read(dis);			
		}
	}
	public ConstantPool getConstantPoolEntry(int index)
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
			header)
		fields_count = dis.readShort();
		System.out.println("field count is "+fields_count);
		fields = new MemberInfo[fields_count];
		for(int i=0;i<fields_count;i++)
		{
			fields[i]=new MemberInfo(dis);
		}
		methods_count = dis.readShort();
		System.out.println("method count is "+methods_count);
		methods = new MemberInfo[methods_count];
		for(int i=0;i<methods_count;i++)
		{
			methods[i]=new MemberInfo(dis);
		}
		attributes_count=dis.readShort();
		System.out.println("attributes count is "+attributes_count);
		attributes = new AttributeInfo[attributes_count];
		for(int i=0;i<attributes_count;i++)
		{
			attributes[i]=new AttributeInfo(dis);
		}
		if(dis.available()>0)
		{
			throw new ClassFormatError("available unread data!");
		}
	}
	
	private static class MemberInfo
	{
		private final short             accessFlags;
		private final short            nameIndex;
		private final short             descriptorIndex;
        private final short            attributesCount;
		private final AttributeInfo[] attributes;
		
		MemberInfo(DataInputStream dis)throws IOException		
		{
			accessFlags=dis.readShort();
			nameIndex=dis.readShort();
			descriptorIndex=dis.readShort();
			attributesCount=dis.readShort();
			attributes=new AttributeInfo[attributesCount];
			System.out.println("attribute count "+attributesCount);
			for(int i=0;i<attributesCount;i++)
			{
				attributes[i]=new AttributeInfo(dis);
			}
		}				
	}
	
}