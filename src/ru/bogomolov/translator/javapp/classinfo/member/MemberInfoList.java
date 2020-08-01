package ru.bogomolov.translator.javapp.classinfo.member;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import ru.bogomolov.translator.javapp.classinfo.constantpool.ConstantPool;
import ru.bogomolov.translator.javapp.classinfo.ClassFileHeader;

public abstract class MemberInfoList<T>
{
	private final short memberCount;
    private final List<T> members;
	protected MemberInfoList(DataInputStream dis,
		ConstantPool constantPool,		
		ClassFileHeader header)throws IOException
	{
		memberCount = dis.readShort();
		System.out.println("count is "+memberCount);
		members = new ArrayList<>(memberCount);
		for(int i=0;i<memberCount;i++)
		{
			members.add(createMemberFromInputStream(dis,
				constantPool,
				header));
		}
	}
	protected abstract T createMemberFromInputStream(DataInputStream dis,
		ConstantPool constantPool,		
		ClassFileHeader header)throws IOException;
	
	short getMemberCount()
	{
		return memberCount;
	}
	T getMemberInfo(int index)
	{
		return members.get(index);
	}
}