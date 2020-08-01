package ru.bogomolov.translator.javapp.classinfo.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantPoolEntry
{
	 /* Constant table */
	private static final byte CONSTANT_UTF8                = 1;
	@Deprecated
	private static final byte CONSTANT_UNICODE             = 2;
	private static final byte CONSTANT_INTEGER             = 3;
	private static final byte CONSTANT_FLOAT               = 4;
	private static final byte CONSTANT_LONG                = 5;
	private static final byte CONSTANT_DOUBLE              = 6;
	private static final byte CONSTANT_CLASS               = 7;
	private static final byte CONSTANT_STRING              = 8;
	private static final byte CONSTANT_FIELD               = 9;
	private static final byte CONSTANT_METHOD              = 10;
	private static final byte CONSTANT_INTERFACEMETHOD     = 11;
	private static final byte CONSTANT_NAMEANDTYPE         = 12;
	/*NEW IN JAVA 8*/
	private static final byte CONSTANT_METHODHANDLE		   = 15;
	private static final byte CONSTANT_METHODTYPE		   = 16;
	private static final byte CONSTANT_INVOKEDYNAMIC	   = 18;
	
	private final byte tag;
	private ClassEntry classEntry;
	private MemberEntry memberEntry;	
	private StringEntry stringEntry;
	private UTF8Entry utf8Entry;
	private WordEntry wordEntry;
	private DWordEntry dwordEntry;	
	private NameAndTypeEntry nameAndTypeEntry;	
	private MethodHandleEntry methodHandleEntry;
	private MethodTypeEntry methodTypeEntry;
	private InvokeDynamicEntry invokeDynamicEntry;
		
	ConstantPoolEntry(DataInputStream dis)throws IOException
	{
		tag=dis.readByte();
		if(tryReadNumber(dis))
		{
			return;
		}
		if(tryReadCharEntry(dis))
		{
			return;
		}		
		if(tryReadClassFileRuntimeEntry(dis))
		{
			return;
		}
		throw new ClassFormatError("tag '"+tag+"' not supported current version parser!");		
	}
	private boolean tryReadNumber(DataInputStream dis)throws IOException
	{		
		boolean result=tryReadWord(dis);
		if(result)
		{
			return true;
		}		
		if(tag==CONSTANT_DOUBLE||tag== CONSTANT_LONG)
		{
			dwordEntry=new DWordEntry(dis);
			return true;
		}			
		return false;
	}
	private boolean tryReadWord(DataInputStream dis)throws IOException
	{
		if(tag==CONSTANT_INTEGER||tag==CONSTANT_FLOAT)		
		{			
				wordEntry=new WordEntry(dis);				
				return true;
		}
		return false;
	}
	private boolean tryReadCharEntry(DataInputStream dis)throws IOException	
	{
		if(tag==CONSTANT_UTF8)
		{
			utf8Entry=new UTF8Entry(dis);				
			return true;
		}
		if(tag==CONSTANT_STRING)
		{
			stringEntry=new StringEntry(dis);
			return true;
		}						
		if(tag== CONSTANT_NAMEANDTYPE)
		{
			nameAndTypeEntry=new NameAndTypeEntry(dis);				
			return true;
		}
		return false;
	}
	private boolean tryReadClassFileRuntimeEntry(DataInputStream dis)throws IOException
	{
		if(tag==CONSTANT_CLASS)
		{				
			classEntry=new ClassEntry(dis);
			return true;
		}           
		if(tag==CONSTANT_FIELD||tag==CONSTANT_METHOD)
		{
			memberEntry=new MemberEntry(dis);
			return true;
		}
		return tryReadVMTTableEntry(dis);			
	}
	private boolean tryReadVMTTableEntry(DataInputStream dis)throws IOException
	{
		if(tag==CONSTANT_INTERFACEMETHOD)
		{				
			memberEntry=new MemberEntry(dis);
			return true;
		}
		return tryReadNewInJVM8(dis);			
	}
	private boolean tryReadNewInJVM8(DataInputStream dis)throws IOException
	{
		if(tag==CONSTANT_METHODHANDLE)
		{
			methodHandleEntry=new MethodHandleEntry(dis);
			return true;
		}
		if(tag==CONSTANT_METHODTYPE)
		{
			methodTypeEntry=new MethodTypeEntry(dis);				
			return true;
		}
		if(tag==CONSTANT_INVOKEDYNAMIC)
		{
			invokeDynamicEntry=new InvokeDynamicEntry(dis);				
			return true;
		}
		return tryReadNewInJVM11(dis);		
	}
	private boolean tryReadNewInJVM11(DataInputStream dis)throws IOException
	{
		System.out.println(dis.toString());
		return false;
	}
	public String asUTF8()
	{
		if(utf8Entry==null)
		{
			return null;
		}
		return utf8Entry.asString();
	}		
	public Integer asInteger()
	{
		if(wordEntry==null)
		{
			return null;
		}
		return wordEntry.asInteger();
	}
	public Float asFloat()
	{
		if(wordEntry==null)
		{
			return null;
		}
		return wordEntry.asFloat();		
	}
	public Long asLong()
	{
		if(dwordEntry==null)
		{
			return null;
		}
		return dwordEntry.asLong();	
	}
	public Double asDouble()
	{
		if(dwordEntry==null)
		{
			return null;
		}
		return dwordEntry.asDouble();	
	}
	public StringEntry asStringEntry()
	{
		return stringEntry;
	}
	public ClassEntry asClassEntry()
	{
		return classEntry;
	}
	public MemberEntry asMemberEntry()
	{
		return memberEntry;
	}
	public NameAndTypeEntry asNameAndTypeEntry()
	{
		return nameAndTypeEntry;
	}
	public MethodHandleEntry asMethodHandleEntry()
	{
		return methodHandleEntry;
	}
	public MethodTypeEntry asMethodTypeEntry()
	{
		return methodTypeEntry;
	}
	public InvokeDynamicEntry asInvokeDynamicEntry()
	{
		return invokeDynamicEntry;
	}
	public boolean isClass()
	{
		return classEntry!=null;
	}
	public boolean isMember()
	{
		return memberEntry!=null;
	}
	public boolean isRef()
	{
		return methodHandleEntry!=null;
	}
	public boolean hasValue()
	{
		return isNumber()||isString();
	}
	private boolean isNumber()
	{
		return tag==CONSTANT_INTEGER||
			tag==CONSTANT_FLOAT||
			tag==CONSTANT_DOUBLE||
			tag==CONSTANT_LONG;
	}
	private boolean isString()
	{
		return tag==CONSTANT_UTF8||tag==CONSTANT_STRING;
	}
	
}