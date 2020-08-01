package ru.bogomolov.translator.javapp.classinfo;

import java.io.DataInputStream;
import java.io.IOException;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool; 
import ru.bogomolov.translator.javapp.classinfo.constantpool.ClassEntry;

public class InheritanceInfo
{
	private final ClassEntry superClassEntry;
	private final InterfaceList interfacesList;
	InheritanceInfo(DataInputStream dis,
		ConstantPool constantPool)throws IOException
	{
		short superClassIndex=dis.readShort();
		interfacesList=new InterfaceList(dis,
			constantPool);
		if(superClassIndex==0)
		{
			return;
		}
		ConstantPoolEntry entry=constantPool.getEntry(superClassIndex);
		if(entry.isClass())
		{
			superClassEntry=entry.asClassInfo();
		}
	}
	
	public short getSuperClassEntry()
	{
		return superClassEntry;
	}
	public boolean hasSuperClass()
	{
		return superClassEntry!=null;
	}
	public InterfaceList getInterfacesList()
	{
		return interfacesList;
	}
}