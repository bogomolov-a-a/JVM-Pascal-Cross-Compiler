package ru.bogomolov.translator.javapp.classinfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPoolEntry;
import ru.bogomolov.translator.javapp.classinfo.constantpool.ClassEntry;

public class InterfaceList
{	
	private final short size;
    private final List<ClassEntry> content;
	InterfaceList(DataInputStream dis,
		ConstantPool constantPool)throws IOException
	{
		size=dis.readShort();
		System.out.println("interfaces count is "+size);
		content=new ArrayList<>(size);
		for(int i=0;i<size;i++)
		{
			readInterfaceInfo(dis,
				constantPool);
		}		
	}
	private void readInterfaceInfo(DataInputStream dis,
		ConstantPool constantPool) throws IOException
	{
		short entryIndex=dis.readShort();
		ConstantPoolEntry entry=constantPool.getEntry(entryIndex);
		if(!entry.isClass())
		{
			throw new IOException("Can't detect class info with index "+entryIndex);
		}
		content.add(entry.asClassEntry());
		System.out.println("interface found with entryIndex "+entryIndex);	
	}
	short getSize()
	{
		return size;
	}
	ClassEntry getInterface(int index)
	{
		return content.get(index);
	}
}