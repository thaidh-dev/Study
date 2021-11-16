import cv2
import time
import numpy as np
import HandTrackingModule as htm
import math
from ctypes import cast, POINTER
from comtypes import CLSCTX_ALL
from pycaw.pycaw import AudioUtilities, IAudioEndpointVolume

################################
wCam, hCam = 700, 10
################################
cap = cv2.VideoCapture(0)
cap.set(3, wCam)
cap.set(4, hCam)
pTime = 0
detector = htm.handDetector(detectionCon=0.8)

# lib pycaw thực hiện chỉnh âm lượng destop
devices = AudioUtilities.GetSpeakers()
interface = devices.Activate(IAudioEndpointVolume._iid_, CLSCTX_ALL, None)
volume = cast(interface, POINTER(IAudioEndpointVolume))
# volume.GetMute()
# volume.GetMasterVolumeLevel()
volRange = volume.GetVolumeRange() # lib pycaw trả về giá trị của âm lương pc [min: -65 (âm lượng = 0), max = 0 (âm lượng  = 100)]
minVol = volRange[0]
maxVol = volRange[1]
vol = 0
volBar = 400
volPer = 0

while cap.isOpened():
    success, img = cap.read()
    detector.findHands(img)
    lmList = detector.findPosition(img, 0, True, 4, 8)
    if len(lmList) != 0:
        # đầu ngón cái
        x1, y1 = lmList[4][1], lmList[4][2]
        # đầu ngón trỏ (là ngón cạnh ngón cái)
        x2, y2 = lmList[8][1], lmList[8][2]
        # trung điểm của 2 ngón
        cx, cy = (x1 + x2) // 2, (y1 + y2) // 2
        cv2.line(img, (x1, y1), (x2, y2), (255, 0, 255), 3)
        cv2.circle(img, (cx, cy), 5, (255, 0, 255), cv2.FILLED)

        # tính cạnh huyền của tam giác vuông
        length = math.hypot(x2 - x1, y2 - y1)

        # VD1:
        #   xp = [1, 2, 3]
        #   fp = [3, 2, 0]
        #   np.interp(2.5, xp, fp)
        #   Kết quả: 1.0
        #   Giải thích:
        #       có tham số 2.5
        #       trong array xp thì 2.5 nằm giữa 2 và 3
        #       thêm 2.5 vào thì array xp sẽ thế này [1, 2, 2.5, 3]
        #       set vị trí nằm giữa 2 và 3 của xp với fp
        #       thì 2.5 sẽ ứng với số nằm giữa 2 và 0 của fp
        #       => là số 1.0
        # VD2: tương tự vd1
        #   np.interp([0, 1, 1.5, 2.72, 3.14], xp, fp)
        #   Kết quả: array([3., 3., 2.5, 0.56, 0.])

        # Hand range (min: 50, max: 200) (ngón cái và ngón trỏ cách nhau 50 thì volume = 0, cách nhau 200 thì volume = 100)
        # Volume Range (min: -65, max: 0)
        vol = np.interp(length, [50, 200], [minVol, maxVol])
        volBar = np.interp(length, [50, 200], [400, 150])
        volPer = np.interp(length, [50, 200], [0, 100])
        # set volume
        volume.SetMasterVolumeLevel(vol, None)
        # khi ngón cái và ngón trỏ chạm nhau - tức là chỉnh volume = 0 thì trung điểm đổi màu
        if length < 50:
            cv2.circle(img, (cx, cy), 15, (0, 255, 0), cv2.FILLED)

    # vẽ cột %
    cv2.rectangle(img, (50, 150), (85, 400), (255, 0, 0), 3)
    cv2.rectangle(img, (50, int(volBar)), (85, 400), (255, 0, 0), cv2.FILLED)
    # vẽ số %
    cv2.putText(img, f'{int(volPer)} %', (40, 450), cv2.FONT_HERSHEY_COMPLEX, 1, (255, 0, 0), 3)

    cTime = time.time()
    fps = 1 / (cTime - pTime)
    pTime = cTime
    cv2.putText(img, f'FPS: {int(fps)}', (40, 50), cv2.FONT_HERSHEY_COMPLEX, 1, (255, 0, 0), 3)
    cv2.imshow("Img", img)
    cv2.waitKey(1)
