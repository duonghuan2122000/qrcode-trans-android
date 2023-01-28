namespace DBHuan.TestQRCode
{
    /// <summary>
    /// Res Dt
    /// </summary>
    /// CreatedBy: dbhuan 21/01/2022
    public class ResponseDto<T>
    {
        public ResponseDto()
        {
            IsSuccess = false;
        }

        public bool IsSuccess { get; set; }

        public string ErrorCode { get; set; }

        public T Data { get; set; }
    }
}