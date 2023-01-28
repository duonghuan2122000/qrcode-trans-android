using Microsoft.EntityFrameworkCore;

namespace DBHuan.TestQRCode
{
    /// <summary>
    /// DbContext ứng dụng
    /// </summary>
    /// CreatedBy: dbhuan 21/01/2022
    public class TestQRCodeDbContext : DbContext
    {
        public TestQRCodeDbContext(DbContextOptions<TestQRCodeDbContext> options) : base(options)
        {
        }

        public virtual DbSet<Config> Config { get; set; }
        public virtual DbSet<Transaction> Transaction { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.HasCharSet("utf8")
                .UseCollation("utf8_general_ci");

            modelBuilder.Entity<Config>(entity =>
            {
                entity.ToTable("config");

                entity.HasComment("Bảng lưu thông tin cấu hình");

                entity.Property(e => e.Id)
                    .HasDefaultValueSql("''")
                    .HasComment("Khóa chính");

                entity.Property(e => e.Key)
                    .IsRequired()
                    .HasMaxLength(255)
                    .HasDefaultValueSql("''");

                entity.Property(e => e.Value)
                    .IsRequired()
                    .HasMaxLength(255)
                    .HasDefaultValueSql("''");
            });

            modelBuilder.Entity<Transaction>(entity =>
            {
                entity.ToTable("transaction");

                entity.HasComment("Bảng lưu thông tin giao dịch");

                entity.Property(e => e.Id)
                    .HasDefaultValueSql("''")
                    .HasComment("Khóa chính");

                entity.Property(e => e.Amount)
                    .HasPrecision(18)
                    .HasComment("Số tiền");

                entity.Property(e => e.CreatedDate)
                    .HasColumnType("datetime")
                    .HasComment("Thời gian tạo giao dịch");

                entity.Property(e => e.MasterMerCode)
                    .IsRequired()
                    .HasMaxLength(50)
                    .HasDefaultValueSql("''")
                    .HasComment("Mã ngân hàng");

                entity.Property(e => e.MerchantCode)
                    .IsRequired()
                    .HasMaxLength(50)
                    .HasDefaultValueSql("''")
                    .HasComment("Mã merchant ngân hàng quy định");

                entity.Property(e => e.TerminalId)
                    .HasMaxLength(50)
                    .HasComment("Mã điểm thu");

                entity.Property(e => e.TxnId)
                    .IsRequired()
                    .HasMaxLength(20)
                    .HasDefaultValueSql("''")
                    .HasComment("Mã đơn hàng");
            });
        }
    }
}