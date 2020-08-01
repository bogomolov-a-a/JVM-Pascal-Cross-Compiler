abstract class MemberInfo
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