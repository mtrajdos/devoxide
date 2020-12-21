import { Directive, HostListener, HostBinding, Input, OnInit } from '@angular/core';

@Directive({
  selector: '[appBetterHighlight]'
})
export class BetterHighlightDirective implements OnInit {

  @Input() defaultBackgroundColor: string = 'transparent';
  @Input('appBetterHighlight') highlightBackgroundColor: string = 'blue';
  @Input() defaultFontColor: string = 'black';
  @Input() highlightFontColor: string = 'white';

  @HostBinding('style.backgroundColor') backgroundColor: string;
  @HostBinding('style.color') fontColor: string;

  // constructor(private elRef: ElementRef, private renderer: Renderer2) { }

  // ngOnInit() {
  //   this.renderer.setStyle(this.elRef.nativeElement, 'background-color', 'blue');
  //   this.renderer.setStyle(this.elRef.nativeElement, 'color', 'white');
  // }

  ngOnInit() {
    this.backgroundColor = this.defaultBackgroundColor;
    this.fontColor = this.defaultFontColor;
  }

  constructor() { }

  @HostListener('mouseenter') mouseOver(eventData: Event) {
    this.backgroundColor = this.highlightBackgroundColor;
    this.fontColor = this.highlightFontColor;
  }

  @HostListener('mouseleave') mouseLeave(eventData: Event) {
    this.backgroundColor = this.defaultBackgroundColor;
    this.fontColor = this.defaultFontColor;
  }

}
