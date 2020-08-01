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
		switch(tag)
		{
			case CONSTANT_UTF8:
			{
				utf8Entry=new UTF8Entry(dis);				
				break;
			}
			case CONSTANT_INTEGER:
			case CONSTANT_FLOAT:				
			{
				wordEntry=new WordEntry(dis);				
				break;
			}
			case CONSTANT_DOUBLE:
			case CONSTANT_LONG:
			{
				dwordEntry=new DWordEntry(dis);
				break;
			}
			case CONSTANT_STRING:
			{
				stringEntry=new StringEntry(dis);
				break;
			}				
			case CONSTANT_CLASS:
			{				
				classEntry=new ClassEntry(dis);
				break;
			}           
			case CONSTANT_FIELD:
			case CONSTANT_METHOD:
			case CONSTANT_INTERFACEMETHOD:
			{				
				memberEntry=new MemberEntry(dis);
				break;
			}
			case CONSTANT_NAMEANDTYPE:
			{
				nameAndTypeEntry=new NameAndTypeEntry(dis);				
				break;
			}
			/*NEW IN JAVA 8*/
			case CONSTANT_METHODHANDLE:
			{
				methodHandleEntry=new MethodHandleEntry(dis);
				break;
			}
			case CONSTANT_METHODTYPE:
			{
				methodTypeEntry=new MethodTypeEntry(dis);				
				break;
			}
			case CONSTANT_INVOKEDYNAMIC:
			{
				invokeDynamicEntry=new InvokeDynamicEntry(dis);				
				break;
			}
			/*new in java 11*/
			default:
			{
				throw new ClassFormatError("tag '"+tag+"' not supported current version parser!");
			}
		}
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
		return tag==CONSTANT_UTF8||
			tag==CONSTANT_INTEGER||
			tag==CONSTANT_FLOAT||
			tag==CONSTANT_DOUBLE||
			tag==CONSTANT_LONG||
			tag==CONSTANT_STRING;
	}
	
}