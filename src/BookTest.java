import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.print.*;
import java.util.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.swing.*;

public class BookTest
{
	public static void main(String[] args)
	{
		JFrame frame=new BookTestFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.show();
	}
}
class BookTestFrame extends JFrame
{
	public BookTestFrame()
	{
		setTitle("HISTORIQUE");
		setSize(WIDTH,HEIGHT);
		
		Container contentPane=getContentPane();
		text=new JTextField();
		contentPane.add(text,BorderLayout.NORTH);
		
		attributes=new HashPrintRequestAttributeSet();
		
		JPanel buttonPanel =new JPanel();
		
		JButton printButton=new JButton("Imprimer");
		buttonPanel.add(printButton);
		printButton.addActionListener(new
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					PrinterJob job=PrinterJob.getPrinterJob();
					job.setPageable(makeBook());
					if (job.printDialog(attributes))
					{
						job.print(attributes);
					}
				}
				catch(PrinterException exception)
				{
					JOptionPane.showMessageDialog(BookTestFrame.this,exception);
				}
			}
		});
		JButton pageSetupButton=new JButton("Mise en page");
		buttonPanel.add(pageSetupButton);
		pageSetupButton.addActionListener(new
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				PrinterJob job=PrinterJob.getPrinterJob();
				pageFormat=job.pageDialog(attributes);
			}
		});
		JButton printPreviewButton=new JButton("Apercu");
		buttonPanel.add(printPreviewButton);
		printPreviewButton.addActionListener(new
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				PrintPreviewDialog dialog =new PrintPreviewDialog(makeBook());
				dialog.show();
			}
		});
		contentPane.add(buttonPanel,BorderLayout.SOUTH);
	}
	public Book makeBook()
	{
		if (pageFormat==null)
		{
			PrinterJob printJob=PrinterJob.getPrinterJob();
			pageFormat=printJob.defaultPage();
		}
		Book book=new Book();
		String message=text.getText();
		Banner banner=new Banner(message);
		int pageCount=banner.getPageCount((Graphics2D)getGraphics(),pageFormat);
		book.append(new CoverPage(message+" "),pageFormat);
		book.append(banner,pageFormat,pageCount);
		return book;
	}
	private JTextField text;
	private PageFormat pageFormat;
	private PrintRequestAttributeSet attributes ;
	
	private static final int WIDTH=300;
	private static final int HEIGHT=100;
	
}

class Banner implements Printable
{
	public Banner(String m)
	{
		message=m;
	}
	public int getPageCount(Graphics2D g2,PageFormat pf)
	{
		if (message.equals(""))return 0;
		FontRenderContext context=g2.getFontRenderContext();
		Font f=new Font("Serif",Font.PLAIN,72);
		Rectangle2D bounds=f.getStringBounds(message,context);
		scale=pf.getImageableHeight();
		double width=scale*bounds.getWidth();
		int pages=(int)Math.ceil(width/pf.getImageableWidth());
		return pages;
	}
	public int print(Graphics g,PageFormat pf,int page)
	throws PrinterException
	{
		Graphics2D g2=(Graphics2D)g;
		if(page>getPageCount(g2,pf)) return Printable.NO_SUCH_PAGE;
		g2.translate(pf.getImageableX(),pf.getImageableY());
		
		drawPage(g2,pf,page);
		return Printable.PAGE_EXISTS;
	}
	public void drawPage(Graphics2D g2,PageFormat pf,int page)
	{
		if(message.equals(""))return;
		page--;
		
		drawCropMarks(g2,pf);
		g2.clip(new Rectangle2D.Double(0,0,pf.getImageableWidth(),pf.getImageableHeight()));
		g2.translate(-page*pf.getImageableWidth(),0);
		g2.scale(scale,scale);
		FontRenderContext context =g2.getFontRenderContext();
		Font f=new Font("Serif",Font.PLAIN,72);
		TextLayout layout=new TextLayout(message,f,context);
		AffineTransform transform=AffineTransform.getTranslateInstance(0,layout.getAscent());
		Shape outline=layout.getOutline(transform);
		g2.draw(outline);
	}
	public void drawCropMarks(Graphics2D g2,PageFormat pf)
	{
		final double c=36;
		double w=pf.getImageableWidth();
		double h=pf.getImageableHeight();
		g2.draw(new Line2D.Double(0,0,0,c));
		g2.draw(new Line2D.Double(0,0,c,0));
		g2.draw(new Line2D.Double(w,0,w,c));
		g2.draw(new Line2D.Double(w,0,w-c,0));
		g2.draw(new Line2D.Double(0,h,0,h-c));
		g2.draw(new Line2D.Double(0,h,c,h));
		g2.draw(new Line2D.Double(w,h,w,h-c));
		g2.draw(new Line2D.Double(w,h,w-c,h));
	}
	private String message;
	private double scale;
}
class CoverPage implements Printable
{
	public CoverPage(String t)
	{
		title=t;
	}
	public int print(Graphics g,PageFormat pf,int page)
	throws PrinterException
	{
		if (page>=1)return Printable.NO_SUCH_PAGE;
		Graphics2D g2=(Graphics2D)g;
		g2.setPaint(Color.black);
		g2.translate(pf.getImageableX(),pf.getImageableY());
		FontRenderContext context=g2.getFontRenderContext();
		Font f=g2.getFont();
		TextLayout layout=new TextLayout(title,f,context);
		float ascent =layout.getAscent();
		g2.drawString(title,0,ascent);
		return Printable.PAGE_EXISTS;
	}
	private String title;
}
class PrintPreviewDialog extends JDialog
{
	public PrintPreviewDialog(Printable p,PageFormat pf,int pages)
	{
		Book book=new Book();
		book.append(p,pf,pages);
		layoutUI(book);
	}
	public PrintPreviewDialog(Book b)
	{
		layoutUI(b);
	}
	public void layoutUI(Book book)
	{
		setSize(WIDTH,HEIGHT);
		
		Container contentPane=getContentPane();
		canvas =new PrintPreviewCanvas(book);
		contentPane.add(canvas,BorderLayout.CENTER);
		
		JPanel buttonPanel=new JPanel();
		
		JButton nextButton=new JButton("Suivant");
		buttonPanel.add(nextButton);
		nextButton.addActionListener(new 
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				canvas.flipPage(1);
			}
		});
		JButton previousButton=new JButton("Précédent");
		buttonPanel.add(previousButton);
		previousButton.addActionListener(new 
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				canvas.flipPage(-1);
			}
		});
		JButton closeButton=new JButton("Fermer");
		buttonPanel.add(closeButton);
		closeButton.addActionListener(new
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);
			}
		});
		contentPane.add(buttonPanel,BorderLayout.SOUTH);
		}
		private PrintPreviewCanvas canvas;
		private static final int WIDTH=300;
		private static final int HEIGHT=300;
}
class PrintPreviewCanvas extends JPanel
{
	public PrintPreviewCanvas(Book b)
	{
		book=b;
		currentPage=0;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		PageFormat pageFormat=book.getPageFormat(currentPage);
		
		double xoff;
		double yoff;
		double scale;
		double px=pageFormat.getWidth();
		double py=pageFormat.getHeight();
		double sx=getWidth()-1;
		double sy=getHeight()-1;
		if(px/py<sx/sy)
		{
			scale=sy/py;
			xoff=0.5*(sx-scale*px);
			yoff=0;
		}
		else 
		{
			scale=sx/px;
			xoff=0;
			yoff=0.5*(sy-scale*py);
		}
		g2.translate((float)xoff,(float)yoff);
		g2.scale((float)scale,(float)scale);
		
		Rectangle2D page=new Rectangle2D.Double(0,0,px,py);
		g2.setPaint(Color.white);
		g2.fill(page);
		g2.setPaint(Color.black);
		g2.draw(page);
		
		Printable printable=book.getPrintable(currentPage);
		try
		{
			printable.print(g2,pageFormat,currentPage);
		}
		catch(PrinterException exception)
		{
			g2.draw(new Line2D.Double(0,0,px,py));
			g2.draw(new Line2D.Double(0,px,0,py));
		}
		
	}
	
	public void flipPage(int by)
	{
		int newPage=currentPage+by;
		if(0<=newPage && newPage<book.getNumberOfPages())
		{
			currentPage=newPage;
			repaint();
		}
	}
	private Book book;
	private int currentPage;
}