//default time ranges

import dayjs from 'dayjs';

export const defaultTimeRanges = {
  今日: [dayjs(), dayjs()],
  昨日: [dayjs().subtract(1, 'days'), dayjs().subtract(1, 'days')],
  本月: [dayjs().startOf('month'), dayjs().endOf('month')],
  上月: [dayjs().subtract(1, 'months').startOf('month'), dayjs().subtract(1, 'months').endOf('month')],
  本年度: [dayjs().startOf('year'), dayjs().endOf('year')],
  上年度: [dayjs().subtract(1, 'years').startOf('year'), dayjs().subtract(1, 'years').endOf('year')],
};

// can not cross the month
export const defaultLimitMonth = {
  今日: [dayjs(), dayjs()],
  昨日: [dayjs().subtract(1, 'days'), dayjs().subtract(1, 'days')],
  本月: [dayjs().startOf('month'), dayjs().endOf('month')],
  上月: [dayjs().subtract(1, 'months').startOf('month'), dayjs().subtract(1, 'months').endOf('month')],
  下个月: [dayjs().subtract(-1, 'months').startOf('month'), dayjs().subtract(-1, 'months').endOf('month')],
};
