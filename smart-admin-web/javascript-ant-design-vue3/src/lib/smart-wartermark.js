//watermark

import dayjs from 'dayjs';

/**
 *  Watermark DOM id
 */
const WATER_MARK_DOM_ID = 'smart_admin_water_mark';
let smartAdminWaterMarkIntervalId = null;

/**
 *
 * Because the z-index of the modal is 1000, 
 * so for the black background of the modal to be hidden,
 *  the z-index is 999
 *
 * @param id
 * @param str
 * @param watermarkId
 * @returns
 */

function setWatermark(id, str) {
  //delete previous watermark
  if (document.getElementById(WATER_MARK_DOM_ID) !== null) {
    document.getElementById(WATER_MARK_DOM_ID).remove();
  }

  // str = str + ' ' + dayjs().format('YYYY-MM-DD HH:mm');
  //
  // //创建一个画布
  // const can = document.createElement('canvas');
  // //设置画布的长宽
  // can.width = 400;
  // can.height = 200;
  //
  // const cans = can.getContext('2d');
  // //旋转角度
  // cans.rotate((-15 * Math.PI) / 150);
  // cans.font = '16px Microsoft JhengHei';
  // //设置填充绘画的颜色、渐变或者模式
  // cans.fillStyle = 'rgba(190, 190, 190, 0.30)';
  // //设置文本内容的当前对齐方式
  // cans.textAlign = 'left';
  // //设置在绘制文本时使用的当前文本基线
  // cans.textBaseline = 'middle';
  // //在画布上绘制填色的文本（输出的文本，开始绘制文本的X坐标位置，开始绘制文本的Y坐标位置）
  // cans.fillText(str, can.width / 8, can.height / 2);
  // const div = document.createElement('div');
  // div.id = WATER_MARK_DOM_ID;
  // div.style.pointerEvents = 'none';
  // div.style.top = '0px';
  // div.style.left = '0px';
  // div.style.position = 'absolute';
  // div.style.zIndex = '999';
  // div.style.width = '100%';
  // div.style.height = '100%';
  // div.style.background = 'url(' + can.toDataURL('image/png') + ') left top repeat';
  // document.getElementById(id).appendChild(div);
}

const watermark = {
  show: function () {
    document.getElementById(WATER_MARK_DOM_ID).style.display = 'block';
  },
  hide: function () {
    document.getElementById(WATER_MARK_DOM_ID).style.display = 'hide';
  },
  // This method is only allowed to be called once
  set: function (id, str) {
    // If there is a watermark, no more calls are allowed
    if (document.getElementById(WATER_MARK_DOM_ID) !== null) {
      alert('The global watermark has already been added, please do not add it again!');
      return;
    }

    setWatermark(id, str);

    //Check the watermark every 1 minute
    smartAdminWaterMarkIntervalId = setInterval(() => {
      setWatermark(id, str);
    }, 60000);

    window.onresize = () => {
      setWatermark(id, str);
    };
  },
  // clear watermark
  clear: function () {
    document.getElementById(WATER_MARK_DOM_ID).remove();
    window.removeEventListener('resize', setWatermark);
    if (smartAdminWaterMarkIntervalId) {
      clearInterval(smartAdminWaterMarkIntervalId);
    }
  },
};
export default watermark;
