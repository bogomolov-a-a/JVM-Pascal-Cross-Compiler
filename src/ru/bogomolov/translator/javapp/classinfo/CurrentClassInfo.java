package ru.bogomolov.translator.javapp.classinfo;

import java.io.DataInputStream;
import java.io.IOException;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ClassEntry;

public class CurrentClassInfo
{	
	private final short accessFlags;
	private final ClassEntry classInfoEntry;
	CurrentClassInfo(DataInputStream dis,
		ConstantPool constantPool)throws IOException
	{
		access_flags=dis.readShort();
		this_class=dis.readShort();
		ConstantPoolEntry entry=constantPool.getEntry(this_class);
		if(entry.isClass())
		{
			classInfoEntry=entry.asClassInfo();
			return;
		}
		throw new IOException("Can't detect CONSTANT_CLASSINFO entry for this class file!");	
	}
	public short getAccessFlags()
	{
		return accessFlags;
	}
    
	public ClassEntry getClassInfoEntry();
	{
		return classInfoEntry;
	}
}